import React, { useState } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createProject } from "../../actions/projectActions";

const Addproject = (props) => {
  const [project, setProject] = useState({
    projectName: "",
    projectIdentifier: "",
    description: "",
    start_date: "",
    end_date: "",
  });

  //error -> redux store
  const onChange = (e) => {
    setProject({
      ...project,
      [e.target.name]: e.target.value,
    });
    // console.log(e.target.value);
  };

  const onSubmit = (e) => {
    e.preventDefault();
    const newProject = {
      projectName: project.projectName,
      projectIdentifier: project.projectIdentifier,
      description: project.description,
      start_date: project.start_date,
      end_date: project.end_date,
    };
    props.createProject(newProject, props.history);
  };

  return (
    <div>
      <div className="register">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <h5 className="display-4 text-center">
                Create / Edit Project form
              </h5>
              <hr />
              {/* 서버하고 같은 name 이어햐 한다 !!!! */}
              <form onSubmit={onSubmit}>
                <div className="form-group">
                  <input
                    type="text"
                    className="form-control form-control-lg "
                    placeholder="Project Name"
                    name="projectName"
                    value={project.projectName}
                    onChange={onChange}
                  />
                </div>
                <div className="form-group">
                  <input
                    type="text"
                    className="form-control form-control-lg"
                    placeholder="Unique Project ID"
                    name="projectIdentifier"
                    value={project.projectIdentifier}
                    onChange={onChange}
                  />
                </div>

                <div className="form-group">
                  <textarea
                    className="form-control form-control-lg"
                    placeholder="Project Description"
                    name="description"
                    value={project.description}
                    onChange={onChange}
                  ></textarea>
                </div>
                <h6>Start Date</h6>
                <div className="form-group">
                  <input
                    type="date"
                    className="form-control form-control-lg"
                    name="start_date"
                    value={project.start_date}
                    onChange={onChange}
                  />
                </div>
                <h6>Estimated End Date</h6>
                <div className="form-group">
                  <input
                    type="date"
                    className="form-control form-control-lg"
                    name="end_date"
                    value={project.end_date}
                    onChange={onChange}
                  />
                </div>

                <input
                  type="submit"
                  className="btn btn-primary btn-block mt-4"
                />
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

Addproject.propTypes = {
  createProject: PropTypes.func.isRequired,
};

export default connect(null, { createProject })(Addproject);
