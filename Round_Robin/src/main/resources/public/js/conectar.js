

var conectar = (function (){

    return{

        api : function (mensaje){

            alert(mensaje)
            fetch("http://localhost:4567/publicar"+"/"+mensaje).then(response => response.json()).
                then(json => console.log(json))


        }

    }


})();