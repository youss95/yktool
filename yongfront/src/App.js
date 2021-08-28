import logo from "./logo.svg";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./components/layout/Header";
import { Route, Router } from "react-router-dom";
import Addproject from "./components/projects/Addproject";
import { Provider } from "react-redux";
import store from "./store";
import UpdateProject from "./components/projects/UpdateProject";
import ProjectBoard from "./components/projectsBoard/ProjectBoard";
import AddProjectTask from "./components/projectsBoard/ProjectTasks/AddProjectTask";
import UpdateProjectTask from "./components/projectsBoard/ProjectTasks/UpdateProjectTask";

function App() {
  return (
    <Provider store={store}>
      <div>
        <Header />
        <Route path="/dashboard" exact={true} component={Dashboard} />
        <Route path="/addProject" exact={true} component={Addproject} />
        <Route
          path="/updateProject/:id"
          exact={true}
          component={UpdateProject}
        />
        <Route path="/projectBoard/:id" exact={true} component={ProjectBoard} />
        <Route
          path="/addProjectTask/:id"
          exact={true}
          component={AddProjectTask}
        />
        <Route
          path="/updateProjectTask/:backlog_id/:pt_id"
          exact={true}
          component={UpdateProjectTask}
        />
      </div>
    </Provider>
  );
}

export default App;
