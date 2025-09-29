var apiclient = (function () {

    var getBlueprintsByAuthor = function (author, callback) {
        $.ajax({
            url: "/blueprints/" + author,
            method: "GET",
            dataType: "json",
            success: function (data) {
                callback(data || []);
            },
            error: function (xhr, status, error) {
                console.error("Error getBlueprintsByAuthor:", status, error);
                callback([]);
            }
        });
    };

    var getBlueprintsByNameAndAuthor = function (author, name, callback) {
        $.ajax({
            url: "/blueprints/" + author + "/" + name,
            method: "GET",
            dataType: "json",
            success: function (data) {
                callback(data || null);
            },
            error: function (xhr, status, error) {
                console.error("Error getBlueprintsByNameAndAuthor:", status, error);
                callback(null);
            }
        });
    };

    return {
        getBlueprintsByAuthor: getBlueprintsByAuthor,
        getBlueprintsByNameAndAuthor: getBlueprintsByNameAndAuthor
    };

})();
