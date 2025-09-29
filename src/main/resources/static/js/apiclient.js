var apiclient = (function () {

    // Obtener todos los planos de un autor
    var getBlueprintsByAuthor = function(author, callback) {
        $.ajax({
            url: "/blueprints/" + author,
            method: "GET",
            success: function(data) {
                callback(data || []);
            },
            error: function() {
                callback([]);
            }
        });
    };

    // Obtener un plano específico por autor y nombre
    var getBlueprintsByNameAndAuthor = function(author, name, callback) {
        $.ajax({
            url: "/blueprints/" + author + "/" + name,
            method: "GET",
            success: function(data) {
                callback(data || null);
            },
            error: function() {
                callback(null);
            }
        });
    };

    return {
        getBlueprintsByAuthor: getBlueprintsByAuthor,
        getBlueprintsByNameAndAuthor: getBlueprintsByNameAndAuthor
    };
})();
