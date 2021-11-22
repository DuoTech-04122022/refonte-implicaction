package com.dynonuggets.refonteimplicaction.service;

import com.dynonuggets.refonteimplicaction.adapter.JobApplicationAdapter;
import com.dynonuggets.refonteimplicaction.controller.ControllerIntegrationTestBase;
import com.dynonuggets.refonteimplicaction.dto.JobApplicationDto;
import com.dynonuggets.refonteimplicaction.dto.JobApplicationRequest;
import com.dynonuggets.refonteimplicaction.exception.NotFoundException;
import com.dynonuggets.refonteimplicaction.model.Company;
import com.dynonuggets.refonteimplicaction.model.JobApplication;
import com.dynonuggets.refonteimplicaction.model.JobPosting;
import com.dynonuggets.refonteimplicaction.model.User;
import com.dynonuggets.refonteimplicaction.repository.JobApplicationRepository;
import com.dynonuggets.refonteimplicaction.repository.JobPostingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static com.dynonuggets.refonteimplicaction.model.ApplyStatusEnum.*;
import static com.dynonuggets.refonteimplicaction.model.ContractTypeEnum.CDI;
import static com.dynonuggets.refonteimplicaction.utils.Message.APPLY_ALREADY_EXISTS_FOR_JOB;
import static com.dynonuggets.refonteimplicaction.utils.Message.JOB_NOT_FOUND_MESSAGE;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class JobApplicationServiceTest extends ControllerIntegrationTestBase {

    @Mock
    JobApplicationRepository applyRepostitory;

    @Mock
    JobPostingRepository jobRepository;

    @Mock
    JobApplicationAdapter applyAdapter;

    @Mock
    AuthService authService;

    @InjectMocks
    JobApplicationService jobApplicationService;

    @Test
    void should_create_apply() {
        // given
        JobApplicationRequest request = new JobApplicationRequest(123L, PENDING);
        JobPosting job = new JobPosting(123L, Company.builder().id(23L).build(), "Mon super job", "Il est trop cool", "Blablabla", "Paris", "140k", null, CDI, Instant.now(), false);
        final User currentUser = User.builder().id(45L).build();
        JobApplication jobApplication = new JobApplication(67L, job, currentUser, request.getStatus(), Instant.now(), false);
        JobApplicationDto expectedDto = new JobApplicationDto(jobApplication.getId(), jobApplication.getJob().getId(), jobApplication.getJob().getTitle(), jobApplication.getJob().getCompany().getName(), jobApplication.getJob().getCompany().getLogo(), jobApplication.getStatus(), CDI);
        given(jobRepository.findById(anyLong())).willReturn(Optional.of(job));
        given(authService.getCurrentUser()).willReturn(currentUser);
        given(applyRepostitory.save(any())).willReturn(jobApplication);
        given(applyAdapter.toDto(any())).willReturn(expectedDto);

        // when
        JobApplicationDto actual = jobApplicationService.createApplyIfNotExists(request);

        //then
        assertThat(actual.getId()).isNotNull();
        assertThat(actual.getId()).isEqualTo(jobApplication.getId());
        assertThat(actual.getStatus()).isEqualTo(jobApplication.getStatus());
        assertThat(actual.getJobId()).isEqualTo(jobApplication.getJob().getId());
        assertThat(actual.getJobTitle()).isEqualTo(jobApplication.getJob().getTitle());
        assertThat(actual.getContractType()).isEqualTo(jobApplication.getJob().getContractType());
        assertThat(actual.getCompanyName()).isEqualTo(jobApplication.getJob().getCompany().getName());
        assertThat(actual.getCompanyImageUri()).isEqualTo(jobApplication.getJob().getCompany().getLogo());
    }

    @Test
    void should_throw_notfound_when_creating_with_no_found_job() {
        // given
        long jobId = 123L;
        JobApplicationRequest request = new JobApplicationRequest(jobId, PENDING);
        NotFoundException expectedException = new NotFoundException(String.format(JOB_NOT_FOUND_MESSAGE, jobId));
        given(jobRepository.findById(anyLong())).willThrow(expectedException);

        // when
        final NotFoundException actualException = assertThrows(NotFoundException.class, () -> jobApplicationService.createApplyIfNotExists(request));

        // then
        assertThat(actualException.getMessage()).isEqualTo(expectedException.getMessage());
    }

    @Test
    void should_throw_exception_when_creating_with_already_applied_job() {
        // given
        long jobId = 123L;
        JobApplicationRequest request = new JobApplicationRequest(jobId, PENDING);
        IllegalArgumentException expectedException = new IllegalArgumentException(String.format(APPLY_ALREADY_EXISTS_FOR_JOB, jobId));
        final JobApplication apply = JobApplication.builder().build();
        JobPosting job = new JobPosting(123L, Company.builder().id(23L).build(), "Mon super job", "Il est trop cool", "Blablabla", "Paris", "140k", null, CDI, Instant.now(), false);
        given(jobRepository.findById(anyLong())).willReturn(Optional.of(job));
        given(applyRepostitory.findByJob(any())).willReturn(Optional.of(apply));

        // when
        final IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, () -> jobApplicationService.createApplyIfNotExists(request));

        // then
        assertThat(actualException.getMessage()).isEqualTo(expectedException.getMessage());
    }

    @Test
    void should_return_all_users_apply() {
        // given
        User currentUser = User.builder().id(123L).build();
        List<JobApplication> expecteds = asList(
                new JobApplication(1L, JobPosting.builder().id(12L).build(), currentUser, PENDING, Instant.now(), false),
                new JobApplication(2L, JobPosting.builder().id(13L).build(), currentUser, CHASED, Instant.now(), false),
                new JobApplication(3L, JobPosting.builder().id(14L).build(), currentUser, INTERVIEW, Instant.now(), false)
        );
        given(authService.getCurrentUser()).willReturn(currentUser);
        given(applyRepostitory.findAllByUserAndArchiveIsFalse(any())).willReturn(expecteds);

        // when
        final List<JobApplicationDto> allAppliesForCurrentUser = jobApplicationService.getAllAppliesForCurrentUser();

        // then
        assertThat(allAppliesForCurrentUser.size()).isEqualTo(expecteds.size());
    }
}