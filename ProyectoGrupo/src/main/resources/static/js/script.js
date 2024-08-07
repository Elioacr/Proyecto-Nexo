
document.addEventListener('DOMContentLoaded', () => {
    const button = document.querySelector('.button');

    button.addEventListener('click', () => {
        button.classList.add('active');

        // Simulate a process completion after 3 seconds
        setTimeout(() => {
            button.classList.remove('active');
        }, 4000); // 2s for progress + 1s delay + 1s checkmark animation
    });
});

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

    
    
    