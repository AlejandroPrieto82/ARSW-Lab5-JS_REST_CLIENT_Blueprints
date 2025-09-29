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
    {
      author: "juan",
      name: "plano4",
      points: [
        { x: 1, y: 2 },
        { x: 3, y: 4 },
        { x: 5, y: 6 },
      ],
    },
    {
      author: "juan",
      name: "plano5",
      points: [
        { x: 7, y: 8 },
        { x: 9, y: 10 },
        { x: 11, y: 12 },
        { x: 13, y: 14 },
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
    {
      author: "maria",
      name: "oficina",
      points: [
        { x: 0, y: 0 },
        { x: 10, y: 10 },
        { x: 20, y: 20 },
        { x: 30, y: 30 },
        { x: 40, y: 40 },
      ],
    },
    {
      author: "maria",
      name: "parque",
      points: [
        { x: 3, y: 3 },
        { x: 6, y: 6 },
        { x: 9, y: 9 },
      ],
    },
    {
      author: "maria",
      name: "escuela",
      points: [
        { x: 12, y: 14 },
        { x: 16, y: 18 },
        { x: 20, y: 22 },
        { x: 24, y: 26 },
      ],
    },
  ];

  // Autor: carlos
  mockdata["carlos"] = [
    {
      author: "carlos",
      name: "torre",
      points: Array.from({ length: 10 }, (_, i) => ({ x: i * 5, y: i * 5 })),
    },
    {
      author: "carlos",
      name: "puente",
      points: Array.from({ length: 12 }, (_, i) => ({ x: i * 3, y: i * 7 })),
    },
    {
      author: "carlos",
      name: "edificio",
      points: Array.from({ length: 8 }, (_, i) => ({ x: i * 10, y: i * 2 })),
    },
  ];

  // Autor: ana
  mockdata["ana"] = [
    {
      author: "ana",
      name: "parque",
      points: Array.from({ length: 6 }, (_, i) => ({ x: i * 2, y: i * 3 })),
    },
    {
      author: "ana",
      name: "escuela",
      points: Array.from({ length: 9 }, (_, i) => ({ x: i * 4, y: i * 5 })),
    },
    {
      author: "ana",
      name: "biblioteca",
      points: Array.from({ length: 7 }, (_, i) => ({ x: i * 6, y: i * 1 })),
    },
  ];

  // Autor: luis
  mockdata["luis"] = [
    {
      author: "luis",
      name: "casaGrande",
      points: Array.from({ length: 15 }, (_, i) => ({ x: i * 1, y: i * 2 })),
    },
    {
      author: "luis",
      name: "estadio",
      points: Array.from({ length: 20 }, (_, i) => ({ x: i * 2, y: i * 3 })),
    },
    {
      author: "luis",
      name: "puerto",
      points: Array.from({ length: 10 }, (_, i) => ({ x: i * 5, y: i * 5 })),
    },
  ];

  // Función pública
  var getBlueprintsByAuthor = function (author, callback) {
    callback(mockdata[author] || []);
  };

  return {
    getBlueprintsByAuthor: getBlueprintsByAuthor,
  };
})();
