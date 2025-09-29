var apimock = (function () {
    var mockdata = [];

    // Planos quemados
    mockdata["juan"] = [
        {author: "juan", name: "plano1", points: [{x: 10, y: 20}, {x: 30, y: 40}]},
        {author: "juan", name: "plano2", points: [{x: 15, y: 25}, {x: 35, y: 45}, {x: 50, y: 60}]}
    ];

    mockdata["maria"] = [
        {author: "maria", name: "casa", points: [{x: 5, y: 5}, {x: 15, y: 15}]},
        {author: "maria", name: "carro", points: [{x: 20, y: 30}, {x: 25, y: 35}, {x: 40, y: 50}]}
    ];

    // Función pública
    var getBlueprintsByAuthor = function (author, callback) {
        callback(mockdata[author]);
    };

    return {
        getBlueprintsByAuthor: getBlueprintsByAuthor
    };
})();
