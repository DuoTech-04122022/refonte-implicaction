export interface Message {
    id?: string;
    content: string;
    sender: string;
    type: string;
    groupId: string;
    sendedAt?: Date;
    avatar?: string
}