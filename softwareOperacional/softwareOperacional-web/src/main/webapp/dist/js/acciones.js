// Variables Iniciales
var chart;
var chart2;
var chart3;
var chart4;

// Grafica chart
$.ajax({
    url: 'http://localhost:8081/softwareOperacional-web/rest/consultas/listarConsulta1', 
    type: 'GET',
    dataType: 'json'
}).done(function (data) {
    console.log(data);
    chart = c3.generate({
        bindto: '#chart',
        data: {
            type: 'bar',
            json: data,
            keys: {
                x: 'producto',
                value: ['venta']
            }
    	},
        axis: {
            x: {
                type: 'category',
                show: false
            }
        },
        bar: {
            width: {
                ratio: 0.5
            }
        }
    });
}).fail(function () {
    console.log("Error al cargar el arreglo chart");
});


// Grafica chart2
$.ajax({
    url: 'http://localhost:8081/softwareOperacional-web/rest/consultas/listarConsulta2', 
    type: 'GET',
    dataType: 'json'
}).done(function (data) {
    console.log(data);
    var dataFull = {};
    var sites = [];
    data.forEach(function (e) {
        sites.push(e.producto);
        dataFull[e.producto] = e.cantidad;
    })

    chart2 = c3.generate({
        bindto: '#chart2',
        data: {
            json: [dataFull],
            keys: {
                value: sites
            },
            type: 'pie'
        }
    });
}).fail(function () {
    console.log("Error al cargar el arreglo chart2");
});


// Grafica chart3
$.ajax({
    url: 'http://localhost:8081/softwareOperacional-web/rest/consultas/listarConsulta3', 
    type: 'GET',
    dataType: 'json'
}).done(function (data) {
    console.log(data);
    chart3 = c3.generate({
        bindto: '#chart3',
        data: {
            type: 'spline',
            json: data,
            keys: {
                x: 'venta',
                value: ['cliente','empleado']
            }
        },
        axis: {
            x: {
                type: 'category',
                show: true
            }
        },
        bar: {
            width: {
                ratio: 0.5
            }
        }
    });
}).fail(function () {
    console.log("Error al cargar el arreglo chart3");
});


// Grafica chart4
$.ajax({
    url: 'http://localhost:8081/softwareOperacional-web/rest/consultas/listarConsulta4', 
    type: 'GET',
    dataType: 'json'
}).done(function (data) {
    console.log(data);
    chart4 = c3.generate({
        bindto: '#chart4',
        data: {
            type: 'bar',
            json: data,
            keys: {
                x: 'fecha',
                value: ['venta']
            }
    	},
        axis: {
            x: {
                type: 'category',
                show: true
            },
    		rotated: true
        }
    });
}).fail(function () {
    console.log("Error al cargar el arreglo chart");
});


