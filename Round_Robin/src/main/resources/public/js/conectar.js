

var conectar = (function (){

    return{

        api : function (mensaje){

            fetch("http://localhost:4568/publicar"+"/"+mensaje)
                .then(response => response.json())
                .then(json => {
                    let json1 = JSON.parse(json)
                    console.log(json1.length)
                    let html = ""
                    for( let i = 0; i < json1.length; i++){
                        html += "<tr>"
                        html+= "<td>" + json1[i].mensaje + "</td>"
                        html+= "<td>" + json1[i].fecha + "</td>"
                        html += "</tr>"
                    }

                    $('#mensaje_body').html(html)

                })


        }

    }


})();