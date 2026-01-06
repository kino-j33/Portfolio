(function() {
  'use strict';

  document.addEventListener('DOMContentLoaded', () => {
    const togglePassword = document.querySelector('#togglePassword');
    const password = document.querySelector('#password');

    if (togglePassword && password) {
      togglePassword.addEventListener('click', () => {
        // type属性の切り替え（password <-> text）
        const isPassword = password.getAttribute('type') === 'password';
        const type = isPassword ? 'text' : 'password';
        password.setAttribute('type', type);

        // アイコンの切り替え（Material Symbols: visibility <-> visibility_off）
        togglePassword.textContent = isPassword ? 'visibility_off' : 'visibility';
      });
    }
  });
})();