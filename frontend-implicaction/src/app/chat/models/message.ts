import { User } from "src/app/shared/models/user";

export interface Message {
    content: string;
    // sender: User;
    sender: string;
    type: string;
    sendedAt: Date;
}