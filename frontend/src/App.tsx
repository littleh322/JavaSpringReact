import React from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Route } from 'react-router-dom';

const App = () =>  {
  return (
    <Router>
      <Route exact path="/" component={AddEmployee}></Route>
      <Route exact path="/view" component={Table}></Route>
    </Router>
  );
}

export default App;
