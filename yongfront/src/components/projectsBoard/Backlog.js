import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import ProjectTask from "./ProjectTasks/ProjectTask";
//task 내용
const Backlog = (props) => {
  let todoItems = [];
  let inProgress = [];
  let doneItem = [];

  const [backlog, setBacklog] = useState([]);
  const tasks = backlog.map((backlog) => (
    <ProjectTask key={backlog.id} backlog={backlog} />
  ));

  for (let i = 0; i < tasks.length; i++) {
    console.log(tasks[i].props.backlog.status);
    if (tasks[i].props.backlog.status === "TO_DO") {
      todoItems.push(tasks[i]);
    } else if (tasks[i].props.backlog.status === "IN_PROGRESS") {
      inProgress.push(tasks[i]);
    } else {
      doneItem.push(tasks[i]);
    }
  }

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/backlog/" + props.id)
      .then((res) => {
        console.log(res.data);
        setBacklog(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return (
    <div>
      <div className="container">
        <div className="row">
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-secondary text-white">
                <h3>TO DO</h3>
              </div>
            </div>
            {todoItems}
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-primary text-white">
                <h3>In Progress</h3>
              </div>
            </div>
            {inProgress}

            {
              //<!-- SAMPLE PROJECT TASK STARTS HERE -->
              //<!-- SAMPLE PROJECT TASK ENDS HERE -->
            }
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-success text-white">
                <h3>Done</h3>
              </div>
            </div>
            {doneItem}
            {
              //<!-- SAMPLE PROJECT TASK STARTS HERE -->
              //<!-- SAMPLE PROJECT TASK ENDS HERE -->
            }
          </div>
        </div>
      </div>
    </div>
  );
};

export default Backlog;
