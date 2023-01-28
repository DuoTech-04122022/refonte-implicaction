export interface Message {
    id: number;
    content: string;
    sender: string;
    type: string;
    sendedAt: Date;
    avatar?: string
}