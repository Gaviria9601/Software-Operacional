// Variables Iniciales
var chart;
var chart2;
var chart3;
var chart4;

// Grafica chart
$
		.ajax(
				{
					url : 'http://localhost:8081/softwareOperacional-web/rest/consultas/listarConsulta1',
					type : 'GET',
					dataType : 'json'
				}).done(function(data) {
			console.log(data);
			chart = c3.generate({
				bindto : '#chart',
				data : {
					type : 'bar',
					json : data,
					keys : {
						x : 'producto',
						value : [ 'venta' ]
					}
				},
				axis : {
					x : {
						type : 'category',
						show : false
					}
				},
				bar : {
					width : {
						ratio : 0.5
					}
				}
			});
		}).fail(function() {
			console.log("Error al cargar el arreglo chart");
		});

// Grafica chart2
$
		.ajax(
				{
					url : 'http://localhost:8081/softwareOperacional-web/rest/consultas/listarConsulta2',
					type : 'GET',
					dataType : 'json'
				}).done(function(data) {
			console.log(data);
			var dataFull = {};
			var sites = [];
			data.forEach(function(e) {
				sites.push(e.producto);
				dataFull[e.producto] = e.cantidad;
			})

			chart2 = c3.generate({
				bindto : '#chart2',
				data : {
					json : [ dataFull ],
					keys : {
						value : sites
					},
					type : 'pie'
				}
			});
		}).fail(function() {
			console.log("Error al cargar el arreglo chart2");
		});

// Grafica chart3
$
		.ajax(
				{
					url : 'http://localhost:8081/softwareOperacional-web/rest/consultas/listarConsulta3',
					type : 'GET',
					dataType : 'json'
				}).done(function(data) {
			console.log(data);
			chart3 = c3.generate({
				bindto : '#chart3',
				data : {
					type : 'area-spline',
					json : data,
					keys : {
						x : 'cliente',
						value : [ 'venta' ]
					}
				},
				axis : {
					x : {
						type : 'category',
						show : true
					}
				},
				bar : {
					width : {
						ratio : 0.5
					}
				}
			});
		}).fail(function() {
			console.log("Error al cargar el arreglo chart3");
		});

// Grafica chart4
$
		.ajax(
				{
					url : 'http://localhost:8081/softwareOperacional-web/rest/consultas/listarConsulta4',
					type : 'GET',
					dataType : 'json'
				}).done(function(data) {
			console.log(data);
			chart4 = c3.generate({
				bindto : '#chart4',
				data : {
					type : 'bar',
					json : data,
					keys : {
						x : 'fecha',
						value : [ 'venta' ]
					}
				},
				axis : {
					x : {
						type : 'category',
						show : true
					},
					rotated : true
				}
			});
		}).fail(function() {
			console.log("Error al cargar el arreglo chart");
		});

// Llamado a Consultas de Filtros

$('#button').click(function() {
	
	
	var parametros = {prod: $("#pro").val(), clien: $("#cli").val()};

    // Filtro Consulta 1
    $.ajax({
        data: parametros,
        url: "http://localhost:8081/softwareOperacional-web/rest/consultas/filtroConsulta1",
        type: "GET",
        dataType: 'json'
    }).done(function (data) {
        console.log(data);
        chart.unload({ids: 'producto,venta'});
        setTimeout(function () {
            chart.load({
                json: data,
                keys: {
                    x: 'producto',
                    value: ['venta']
                }
            });
        }, 500);
    }).fail(function (data) {
        console.log("No se pudo realizar el filtro Chart");
    });
    
    
    // Filtro Consulta 2
    $.ajax({
        data: parametros,
        url: "http://localhost:8081/softwareOperacional-web/rest/consultas/filtroConsulta2",
        type: "GET",
        dataType: 'json'
    }).done(function (data) {
        console.log("Filtro Realizado Chart2 " + data);
        chart2.unload({ids: 'sites'});
        var dataFull = {};
        var sites = [];
        data.forEach(function (e) {
            sites.push(e.producto);
            dataFull[e.producto] = e.cantidad;
        });
        setTimeout(function () {
            chart2.load({
                json: [dataFull],
                keys: {
                    value: sites
                },
                type: 'pie'
            });
        }, 500);
    }).fail(function () {
        console.log("No se pudo realizar el filtro Chart2");
    });
    
 // Filtro Consulta 3
    $.ajax({
        data: parametros,
        url: "http://localhost:8081/softwareOperacional-web/rest/consultas/filtroConsulta3",
        type: "GET",
        dataType: 'json'
    }).done(function (data) {
        console.log("Filtro Realizado Chart3 " + data);
        chart3.unload({ids: 'cliente,venta'});
        setTimeout(function () {
            chart3.load({
                json: data,
                keys: {
                    x: 'cliente',
                    value: ['venta']
                }
            });
        }, 500);
    }).fail(function () {
        console.log("No se pudo realizar el filtro Chart3");
    });
	
 // Filtro Consulta 4
    $.ajax({
        data: parametros,
        url: "http://localhost:8081/softwareOperacional-web/rest/consultas/filtroConsulta4",
        type: "GET",
        dataType: 'json'
    }).done(function (data) {
        console.log(data);
        chart.unload({ids: 'fecha,venta'});
        setTimeout(function () {
            chart.load({
                json: data,
                keys: {
                    x: 'fecha',
                    value: ['venta']
                }
            });
        }, 500);
    }).fail(function (data) {
        console.log("No se pudo realizar el filtro Chart");
    });
	
});


