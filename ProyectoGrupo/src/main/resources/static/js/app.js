document.addEventListener("DOMContentLoaded", function() {
    const togglePassword = document.querySelector("#togglePassword");
    const password = document.querySelector("#password");

    if (togglePassword && password) {
        togglePassword.addEventListener("click", function() {
            // Toggle the type attribute using getAttribute() method
            const type = password.getAttribute("type") === "password" ? "text" : "password";
            password.setAttribute("type", type);

            // Toggle the eye slash icon
            this.classList.toggle("fa-eye-slash");
        });
    }

    const toggleConfirmPassword = document.querySelector("#toggleConfirmPassword");
    const confirmPassword = document.querySelector("#confirm-password");

    if (toggleConfirmPassword && confirmPassword) {
        toggleConfirmPassword.addEventListener("click", function() {
            // Toggle the type attribute using getAttribute() method
            const type = confirmPassword.getAttribute("type") === "password" ? "text" : "password";
            confirmPassword.setAttribute("type", type);

            // Toggle the eye slash icon
            this.classList.toggle("fa-eye-slash");
        });
    }
});
