(function() {
  'use strict';

  document.addEventListener('DOMContentLoaded', () => {

    // ===== Modal (<dialog> version) =====
    const cards = document.querySelectorAll('.work-card');

    if (cards.length) {
      cards.forEach((card) => {
        card.addEventListener('click', () => {
          const modalId = card.dataset.modal;
          if (!modalId) return;

          const modal = document.getElementById(modalId);
          if (!modal) return;

          // ブラウザ標準の showModal メソッドで表示
          if (typeof modal.showModal === 'function') {
            modal.showModal();
          }
        });
      });

      // 各モーダル内の閉じる動作の制御
      const modals = document.querySelectorAll('.modal');
      modals.forEach((modal) => {
        // 1. 閉じるボタン（×ボタン）をクリックしたとき
        const closeBtn = modal.querySelector('.modal-close');
        if (closeBtn) {
          closeBtn.addEventListener('click', () => {
            modal.close();
          });
        }

        // 2. モーダルの背景部分をクリックしたとき
        modal.addEventListener('click', (e) => {
          // e.target が dialog 本体（外枠）であれば背景部分と判定して閉じる
          if (e.target === modal) {
            modal.close();
          }
        });
      });
    }

    // ===== Smooth Scroll =====
    const scrollLinks = document.querySelectorAll('a[data-scroll]');

    if (scrollLinks.length) {
      scrollLinks.forEach((link) => {
        link.addEventListener('click', (e) => {
          e.preventDefault();

          const targetId = link.getAttribute('data-scroll');
          if (!targetId) return;

          const target = document.getElementById(targetId);
          if (!target) return;

          // 指定した要素までスムーズにスクロール
          target.scrollIntoView({
            behavior: 'smooth'
          });

          // ブラウザの履歴にフラグメントを残さずURLを維持
          history.replaceState(null, '', location.pathname);
        });
      });
    }

  });
})();