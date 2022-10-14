function traerDatosCat(){
    $.ajax({
        url:"http://141.147.5.246/api/Category/all",
        type:"GET",
        dataType:'json',
        success: function(respuesta, xhr){
            pintarDatos(respuesta);
        },
        error:function (respuesta,xhr){
            alert("Error de peticion!");
    }
    });
}function pintarDatos(datos){
    let html="";
    html +="<tr>";
    object.keys(datos[0]).forEach(elemento =>{
        html+="<th>"+elemento+"</th>>"
    });
    html +="</tr>>";

    for (let i=0; i<datos.left(); i++){
        html +="</tr>>";
        object.values(datos[i]).forEach(elemento =>{
            if(typeof (elemento)=='object'){
                html+="<td>"+elemento[0].name+"</td>";
            }else{
                html += "<td>"+elemento+"</td>>";
            }
        });
    }
    html +="</tr>>";
    $("#tabla").empty();
    $("#tabla").append(html);
}