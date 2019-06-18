import User from "../interfaces/UserIndo.interface";

export function setUser(user) {
  return {
    type: "SET_USER",
    userId: user.userId,
    uname: user.uname,
    isActive: user.isActive,
    isFree: user.isFree
  };
}

export function unSetUser() {
  return {
    type: "RESET_USER"
  };
}
