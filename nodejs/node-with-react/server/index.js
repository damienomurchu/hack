const express = require("express");
require('./services/passport');

const app = express();

require('./routes/authRoutes')(app);

app.get("/", (req, res) => {
  res.send("Server accepting requests!");
});

const PORT = process.env.PORT || 5000;
app.listen(PORT);
console.log("Listening on port " + PORT);
