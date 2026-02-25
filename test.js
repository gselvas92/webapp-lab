// create a simple express server with one endpoint /hello

const app = express();
const port = 3000;

app.get('/hello', (req, res) => {
  res.send('Hello World!');
});

app.listen(port, () => {
  console.log(`Server running at http://localhost:${port}`);
});

//

//crea una función para sumar dos números
function sum(a, b) {
  return a + b;
} 
