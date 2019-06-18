import * as React from "react";
import * as ReactDOM from "react-dom";
import App from "./components/App";
import { store } from './components/store'

const ROOT = document.querySelector(".container");

const render = () => ReactDOM.render(<App />, ROOT);

store.subscribe(render);
render();