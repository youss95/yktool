import React from "react";
import { Link } from "react-router-dom";

const ProjectTask = (props) => {
  const {
    summary,
    acceptanceCriteria,
    id,
    projectSequence,
    priority,
    projectIdentifier,
  } = props.backlog;
  let priorityString;
  let priorityClass;
  if (priority === 1) {
    priorityClass = "bg-danger text-light";
    priorityString = "HIGH";
  } else if (priority === 2) {
    priorityClass = "bg-warning text-light";
    priorityString = "MEDIUM";
  } else {
    priorityClass = "bg-success text-light";
    priorityString = "LOW";
  }
  return (
    <div>
      <div className="card mb-1 bg-light">
        {
          //<!-- class추가하고 css수정해도 됨 -->
        }
        <div className={`card-header text-primary ${priorityClass}`}>
          {projectSequence}, 우선순위 : {priorityString}
        </div>
        <div className="card-body bg-light">
          <h5 className="card-title">{summary}</h5>
          <p className="card-text text-truncate ">{acceptanceCriteria}</p>
          <Link
            to={`/updateProjectTask/${projectIdentifier}/${projectSequence}`}
            className="btn btn-primary"
          >
            View / Update
          </Link>

          <button className="btn btn-danger ml-4">Delete</button>
        </div>
      </div>
    </div>
  );
};

export default ProjectTask;
