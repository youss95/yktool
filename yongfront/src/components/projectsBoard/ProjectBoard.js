import React from "react";
import { Link } from "react-router-dom";
import Backlog from "./Backlog";

const ProjectBoard = (props) => {
  const { id } = props.match.params; //get방식 받는거라 생각

  return (
    <div>
      <div className="container">
        <Link to={"/addProjectTask/" + id} className="btn btn-primary mb-3">
          <i className="fas fa-plus-circle"> Create Project Task</i>
        </Link>
        <br />
        <hr />
        <Backlog id={id} />
      </div>
    </div>
  );
};

export default ProjectBoard;
