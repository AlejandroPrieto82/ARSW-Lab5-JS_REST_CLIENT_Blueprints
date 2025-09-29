var app = (function () {
    // Estado privado
    var author = "";
    var blueprints = [];

    // Cambiar autor
    var setAuthor = function (newAuthor) {
        author = newAuthor;
        $("#selectedAuthor").text(author);
    };

    // Consultar planos por autor y actualizar tabla
    var updateBlueprints = function (authorName) {
        setAuthor(authorName);

        apimock.getBlueprintsByAuthor(author, function (data) {
            if (!data) {
                $("#blueprintsTable").empty();
                $("#totalPoints").text(0);
                return;
            }

            // Transformar: solo nombre y # puntos
            blueprints = data.map(function (bp) {
                return {
                    name: bp.name,
                    points: bp.points.length
                };
            });

            // Limpiar tabla
            $("#blueprintsTable").empty();

            // Renderizar filas
            blueprints.map(function (bp) {
                $("#blueprintsTable").append(
                    "<tr><td>" + bp.name + "</td><td>" + bp.points + "</td></tr>"
                );
            });

            // Calcular total de puntos
            var totalPoints = blueprints.reduce(function (acc, bp) {
                return acc + bp.points;
            }, 0);

            $("#totalPoints").text(totalPoints);
        });
    };

    return {
        updateBlueprints: updateBlueprints
    };
})();
