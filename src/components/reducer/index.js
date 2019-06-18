export default (state, action) => {
  switch (action.type) {
    case "SET_USER":
      return {
        ...state,
        userId: action.userId,
        uname: action.uname,
        isActive: action.isActive,
        isFree: action.isFree
      };
    case "RESET_USER":
      return {
        ...state,
        userId: 0,
        uname: "",
        isActive: false,
        isFree: false
      };
    default:
      return state;
  }
};
