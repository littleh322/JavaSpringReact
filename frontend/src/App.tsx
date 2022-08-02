import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { AddEmployee } from './components/AddEmployee';
import { Table } from './components/Table';

const App = () =>  {
  return (
    <Router>
      <Routes>
      <Route path="/" element={<AddEmployee />} />
      <Route path="/view" element={<Table/>}/>
      </Routes>
    </Router>
  );
}

export default App;
