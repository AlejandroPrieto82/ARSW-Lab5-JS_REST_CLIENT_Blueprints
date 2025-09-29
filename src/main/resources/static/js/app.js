var app = (function () {
    // Privado
    var selectedAuthor = "";
    var blueprintsList = [];

    // Cambiar autor seleccionado
    function setAuthor(author) {
        selectedAuthor = author;
        $("#selectedAuthor").text(author);
    }

    // Actualizar listado de planos desde la API REST
    function updateBlueprints(author) {
        setAuthor(author);

        // Limpiar tabla
        $("#blueprintsTable").empty();

        // ------------------------------
        // OPCIÓN 1: API REST real
        // ------------------------------
        $.ajax({
            url: "/blueprints/" + author, // Endpoint REST
            method: "GET",
            success: function(data) {
                if (!data || data.length === 0) {
                    $("#totalPoints").text(0);
                    return;
                }

                // Guardar listado transformado (solo nombre y cantidad de puntos)
                blueprintsList = data.map(function(bp) {
                    return { name: bp.name, pointsCount: bp.points.length };
                });

                // Llenar tabla HTML con los planos
                blueprintsList.forEach(function(bp) {
                    $("#blueprintsTable").append(
                        "<tr><td>" + bp.name + "</td><td>" + bp.pointsCount + "</td></tr>"
                    );
                });

                // Total de puntos usando listado original
                var totalOriginal = data.reduce(function(acc, bp) {
                    return acc + bp.points.length;
                }, 0);

                $("#totalPoints").text(totalOriginal);
            },
            error: function() {
                alert("Error: autor no encontrado o fallo en el servidor.");
                $("#totalPoints").text(0);
            }
        });

        // ------------------------------
        // OPCIÓN 2: Para pruebas locales con apimock.js
        // ------------------------------
        /*
        apimock.getBlueprintsByAuthor(author, function (data) {
            if (!data || data.length === 0) {
                $("#totalPoints").text(0);
                return;
            }

            blueprintsList = data.map(function(bp) {
                return { name: bp.name, pointsCount: bp.points.length };
            });

            blueprintsList.forEach(function(bp) {
                $("#blueprintsTable").append(
                    "<tr><td>" + bp.name + "</td><td>" + bp.pointsCount + "</td></tr>"
                );
            });

            var totalOriginal = data.reduce(function(acc, bp) {
                return acc + bp.points.length;
            }, 0);

            $("#totalPoints").text(totalOriginal);
        });
        */
    }

    // Asociar botón al click
    $(document).ready(function () {
        $("#getBlueprints").click(function () {
            var author = $("#author").val().trim();
            if (author !== "") {
                updateBlueprints(author);
            } else {
                alert("Ingrese un autor válido");
            }
        });
    });

    // Público
    return {
        setAuthor: setAuthor,
        updateBlueprints: updateBlueprints
    };
})();
