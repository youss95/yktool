import React from "react";
import CreateProjectButton from "./projects/CreateProjectButton";
import ProjectsItem from "./projects/ProjectsItem";

const Dashboard = () => {
  return (
    <div>
      <div className="projects">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="display-4 text-center">Projects</h1>
              <br />
              <CreateProjectButton />
              <br />
              <hr />
              <ProjectsItem />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
