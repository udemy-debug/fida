"use strict";

var toDo = {
  _id: 0,
  cacheDom: function () {
    this.toDo = [];
    this.main = document.getElementById("main");
  },
  init: function () {
    // must run first
    this.cacheDom();
    this.displayToDos();
  },
  displayToDos: function () {
    let htmltext = "";
    fetch(`http://localhost:8080/api/v1/todos`)
      .then((res) => res.json())
      .then((x) => {
        console.log(x);
        let line, beschreibung, datum;
        for (let i = 0; i < x.length; i += 1) {
          line = x[i]["id"];
          datum = x[i]["erledigungsDatum"];
          beschreibung = x[i]["beschreibung"];
          htmltext += `<div class='myDiv'> ** ${line} ** ${datum} ** ${beschreibung} ** </div><button onClick="toDo.deleteToDo(${line})">delete ${line}</button>`;
        }
        this.main.innerHTML = htmltext;
      });
  },
  deleteToDo(id) {
    fetch("http://localhost:8080/api/v1/todos/" + id, {
      method: "DELETE",
    })
      .then((res) => res.text())
      .then((res) => console.log(res));
    this.displayToDos();
  },
};

toDo.init();
