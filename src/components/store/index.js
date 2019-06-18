import { createStore } from "redux";
import reducer from "../reducer";

const initialState = {
  userId: 0,
  uname: "",
  isActive: false,
  isFree: false
};

export const store = createStore(reducer, initialState);
