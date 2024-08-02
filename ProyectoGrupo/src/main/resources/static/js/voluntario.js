// Filtro eventos por categoria
function obtenerCategoriaDeRuta() {
    // Obtiene la ruta actual
    const path = window.location.pathname;

	// Extrae la categoría de la ruta, suponiendo que la ruta sigue el formato '/eventos/filtrarCategoria/{}'
	const match = path.match(/\/eventos\/filtrarCategoria\/(.+)/);

    // Retorna la categoría decodificada o null si no hay coincidencia
    return match ? decodeURIComponent(match[1]) : null;
}

// Filtro eventos por organizacion
function obtenerOrganizacionDeRuta() {
    // Obtiene la ruta actual
    const path = window.location.pathname;

	// Extrae la organizacion de la ruta, suponiendo que la ruta sigue el formato '/eventos/filtrarOrganizacion/{}'
	const match = path.match(/\/eventos\/filtrarOrganizacion\/(.+)/);

    // Retorna la categoría decodificada o null si no hay coincidencia
    return match ? decodeURIComponent(match[1]) : null;
}

function obtenerOrganizacionCategoriaDeRuta() {
	// Obtiene la ruta actual
    const path = window.location.pathname;

	// Extrae la organización y la categoría de la ruta, suponiendo que la ruta sigue el formato '/eventos/filtrar/{organizacion}/{categoria}'
    const match = path.match(/\/eventos\/filtrar\/([^\/]+)\/([^\/]+)/);

    // Retorna un objeto con la organización y la categoría decodificadas, o null si no hay coincidencia
    return match ? { organizacion: decodeURIComponent(match[1]), categoria: decodeURIComponent(match[2]) } : null;
}
document.addEventListener('DOMContentLoaded', function() {
    const selectCat = document.getElementById('selectFiltroCategoria');
    const selectOrg = document.getElementById('selectFiltroOrganizacion');
    const filtros = obtenerOrganizacionCategoriaDeRuta();
    if(filtros == null) {
	    const category = obtenerCategoriaDeRuta();
	    const organizacion = obtenerOrganizacionDeRuta();
	    
	    // Establece el valor seleccionado del select
	    if (category != null) {
	        selectCat.value = category;
	    } else {
	        selectCat.value = 'Todas las Categorias';
	    }
	    
	    if (organizacion != null) {
	        selectOrg.value = organizacion;
	    } else {
	        selectOrg.value = 'Todas las Organizaciones';
	    }
	} else {
		selectCat.value = filtros.categoria;
    	selectOrg.value = filtros.organizacion;
	}

});
document.getElementById('selectFiltroCategoria').addEventListener('change', function() {
    const selectOrg = document.getElementById('selectFiltroOrganizacion');
    if(selectOrg.value != 'Todas las Organizaciones') {
		if(this.value === 'Todas las Categorias') {
			window.location.href = '/eventos/filtrarOrganizacion/' + encodeURIComponent(selectOrg.value);
		} else {
			window.location.href = '/eventos/filtrar/' + encodeURIComponent(selectOrg.value) + '/' + encodeURIComponent(this.value);
		}
	} else {
		if(this.value === 'Todas las Categorias') {
			window.location.href = "/voluntario";
		} else {
			window.location.href = '/eventos/filtrarCategoria/' + encodeURIComponent(this.value);
		}
	}
});

document.getElementById('selectFiltroOrganizacion').addEventListener('change', function() {
    const selectCat = document.getElementById('selectFiltroCategoria');
    if(selectCat.value != 'Todas las Categorias') {
		if(this.value === 'Todas las Organizaciones') {
			window.location.href = "/eventos/filtrarCategoria/" + encodeURIComponent(selectCat.value);
		} else {
			window.location.href = '/eventos/filtrar/' + encodeURIComponent(this.value) + '/' + encodeURIComponent(selectCat.value);
		}
	} else {
		if(this.value === 'Todas las Organizaciones') {
			window.location.href = "/voluntario";
		} else {
			window.location.href = '/eventos/filtrarOrganizacion/' + encodeURIComponent(this.value);
		}
	}
});
