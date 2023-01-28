import { RxStompService } from '../services/rx-stomp/rx-stomp.service';
import { myRxStompConfig } from '../config/rx-stomp.config';

export function rxStompServiceFactory() {
  const rxStomp = new RxStompService();
  rxStomp.configure(myRxStompConfig);
  rxStomp.activate();
  return rxStomp;
}