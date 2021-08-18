import React, { useEffect, useState } from "react";

import CreateProjectButton from "./projects/CreateProjectButton";
import ProjectsItem from "./projects/ProjectsItem";
import PropTypes from "prop-types";
import axios from "axios";

const Dashboard = () => {
  //componentdidmount
  const [dashboard, setDashboard] = useState([]);
  useEffect(() => {
    axios
      .get("http://localhost:8080/api/project/all")
      .then((res) => {
        console.log(res.data);
        setDashboard(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);
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
              {dashboard.map((dashboard) => (
                <ProjectsItem key={dashboard.id} dashboard={dashboard} />
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = (state) => ({
  project: state.project, //index.js에 있는  project 추출
});

export default Dashboard;
