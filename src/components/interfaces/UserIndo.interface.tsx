export default interface UserProfile {
    fname: string,
    lname: string,
    email: string,
    uname: string,
    isActive: boolean
}

export default interface UserActiveFree {
    uname: string,
    isActive: boolean,
    isFree: boolean
}