class LoginBox extends React.Component {
  constructor(props) {
    super(props);
    
    this.users = [
      {
        username: "Isaac",
        password: "cheese123"
      }
    ]
    
    this.state = {
      testUser: {
        username: "",
        password: ""
      }
    }
    
    this.handleSubmit = this.handleSubmit.bind(this);
    this.changeVal = this.changeVal.bind(this);
  }

  handleSubmit(e) {
    var testUser = this.state.testUser;
    
    for(var user of this.users) {
      if(testUser.username == user.username && testUser.password == user.password) {
        alert("Success")
      } else {
        alert("Failure")
      }
    }
    
    e.preventDefault();
  }
  
  changeVal(prop, event) {
    var otherProp = (prop == "username") ? "password" : "username";
    this.setState((prevState, props) => {
      return {
        testUser: {
          [prop]: event.target.value,
          [otherProp]: prevState.testUser[otherProp]
        }
      }
    });
    console.log(this.state.testUser)
  }
  
  render() {
    return (
      <div className="container panel panel-default" id="login-box">
        <h1 className="text-center">Login</h1>
        <form className="form-horizontal panel-body" onSubmit={this.handleSubmit}>
          <div className="form-group">
            <input type="text" value={this.state.testUser.username} className="form-control" placeholder="Username" onChange={() => this.changeVal("username", event)} />
          </div>
          <div className="form-group">
            <input type="password" value={this.state.testUser.password} className="form-control" placeholder="Password" onChange={() => this.changeVal("password", event)}/>
          </div>
          <div className="form-group text-center">
            <input type="submit" className="btn btn-info" />
          </div>
        </form>
    </div>
    );
  }
}

ReactDOM.render(<LoginBox />, document.getElementById("demo"));