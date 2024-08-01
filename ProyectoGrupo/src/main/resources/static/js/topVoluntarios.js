function getCategoryFromPath() {
    // Obtiene la ruta actual
    const path = window.location.pathname;

	// Extrae la categoría de la ruta, suponiendo que la ruta sigue el formato '/eventos/filtrarCategoria/{}'
	const match = path.match(/\/topvoluntarios\/(.+)/);

    // Retorna la categoría decodificada o null si no hay coincidencia
    return match ? decodeURIComponent(match[1]) : null;
}

document.addEventListener('DOMContentLoaded', function() {
    const select = document.getElementById('selectFiltro');
    const category = getCategoryFromPath();

    // Establece el valor seleccionado del select
    if (category != null) {
        select.value = category;
    } else {
        select.value = 'Global';
    }
});
document.getElementById('selectFiltro').addEventListener('change', function() {
	if(this.value === 'Global') {
		window.location.href = "/topvoluntarios";
	} else {
		window.location.href = '/topvoluntarios/' + encodeURIComponent(this.value);
	}
});