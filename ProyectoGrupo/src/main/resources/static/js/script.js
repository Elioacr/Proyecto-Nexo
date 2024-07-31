document.addEventListener('DOMContentLoaded', () => {
    const navbarLinks = document.querySelectorAll('.nav-link');
    navbarLinks.forEach(link => {
        link.addEventListener('click', (e) => {
            e.preventDefault();
            document.querySelector(link.getAttribute('href')).scrollIntoView({
                behavior: 'smooth'
            });
        });
    });

    const voluntariosSection = document.querySelector('.voluntarios-section');
    window.addEventListener('scroll', () => {
        const sectionTop = voluntariosSection.getBoundingClientRect().top;
        const viewportHeight = window.innerHeight;
        if (sectionTop <= viewportHeight * 0.75) {
            voluntariosSection.style.display = 'block';
            setTimeout(() => {
                voluntariosSection.style.opacity = 1;
            }, 100);
        }
    });
});
