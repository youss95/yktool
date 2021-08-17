import logo from "./logo.svg";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./components/layout/Header";
import { Route, Router } from "react-router-dom";
import Addproject from "./components/projects/Addproject";
import { Provider } from "react-redux";
import store from "./store";

function App() {
  return (
    <Provider store={store}>
      <div>
        <Header />
        <Route path="/dashboard" exact={true} component={Dashboard} />
        <Route path="/addProject" exact={true} component={Addproject} />
      </div>
    </Provider>
  );
}

export default App;
