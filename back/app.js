const express = require('express')
const bodyParser = require('body-parser')
const path = require('path')
const cors = require('cors')

const app = express()

app.use(express.static(__dirname))

app.use(bodyParser.json())

app.use(cors())

var foods = [
  { 'id': 1, 'name': 'Donuts' },
  { 'id': 2, 'name': 'Pizza' },
  { 'id': 3, 'name': 'Tacos' }
]

var books = [
  { 'name': 'Book 1', 'author': 'Author 1' },
  { 'name': 'Book 2', 'author': 'Author 2' },
  { 'name': 'Book 2', 'author': 'Author 3' }
]

var movies = [
  { 'title': 'Ghostbusters' },
  { 'title': 'Star Wars' },
  { 'title': 'Batman Begins' }
]

app.get('/api/books', function (req, res) {
  res.send(books)
})

app.get('/api/movies', function (req, res) {
  res.send(movies)
})

app.get('/api/food', function (req, res) {
  res.send(foods)
})

// POST endpoint for creating a new food
app.post('/api/food', function (req, res) {
  // calculate the next ID
  let id = 1;
  if (foods.length > 0) {
    let maximum = Math.max.apply(Math, foods.map(function (f) { return f.id; }));
    id = maximum + 1;
  }
  let new_food = {"id": id, "name": req.body.name};
  foods.push(new_food);
  res.send(new_food);
});

// PUT endpoint for editing food
app.put('/api/food/:id', function (req, res) {
  let id = req.params.id;
  let f = foods.find(x => x.id == id);
  f.name = req.body.name;
  res.send(f);
});

// DELETE endpoint for deleting food
app.delete('/api/food/:id', function (req, res) {
  let id = req.params.id;
  let f = foods.find(x => x.id == id);
  foods = foods.filter(x => x.id != id);
  res.send(f);
});

// HTTP listener
app.listen(3000, function () {
  console.log('Example listening on port 3000!');
});
module.exports = app;
