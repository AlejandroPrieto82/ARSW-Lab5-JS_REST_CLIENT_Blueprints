var apimock = (function () {
    var mockdata = [];

    // Planos quemados de Juan
    mockdata["juan"] = [
        {
            author: "juan",
            name: "plano1",
            points: [
                { x: 10, y: 20 },
                { x: 30, y: 40 },
                { x: 50, y: 60 },
            ],
        },
        {
            author: "juan",
            name: "plano2",
            points: [
                { x: 15, y: 25 },
                { x: 35, y: 45 },
                { x: 50, y: 60 },
                { x: 70, y: 80 },
            ],
        },
        {
            author: "juan",
            name: "plano3",
            points: [
                { x: 5, y: 5 },
                { x: 10, y: 10 },
                { x: 15, y: 15 },
                { x: 20, y: 20 },
                { x: 25, y: 25 },
            ],
        },
    ];

    // Planos quemados de Maria
    mockdata["maria"] = [
        {
            author: "maria",
            name: "casa",
            points: [
                { x: 5, y: 5 },
                { x: 15, y: 15 },
                { x: 25, y: 25 },
            ],
        },
        {
            author: "maria",
            name: "carro",
            points: [
                { x: 20, y: 30 },
                { x: 25, y: 35 },
                { x: 40, y: 50 },
                { x: 60, y: 70 },
            ],
        },
    ];

    // Función pública
    var getBlueprintsByAuthor = function (author, callback) {
        callback(mockdata[author] || []);
    };

    var getBlueprintsByNameAndAuthor = function(author, blueprintName, callback) {
        var planes = mockdata[author] || [];
        var bp = planes.find(function(p) { return p.name === blueprintName; }) || null;
        callback(bp);
    };

    return {
        getBlueprintsByAuthor: getBlueprintsByAuthor,
        getBlueprintsByNameAndAuthor: getBlueprintsByNameAndAuthor
    };
})();
