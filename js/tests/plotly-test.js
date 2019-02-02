const plotly_credentials = require('../credentials/plotly_credentials.json');
const plotly = require('plotly')(plotly_credentials.username, plotly_credentials.api_key);

let trace1 = {
    x: [1, 2, 3, 4],
    y: [10, 15, 13, 17],
    type: "scatter"
};
let data = [trace1];
let graphOptions = { filename: "test", fileopt: "overwrite" };
plotly.plot(data, graphOptions, function(err, msg) {
    console.log(msg);
});