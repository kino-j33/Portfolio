# Yamashita Portfolio

本プロジェクトは、Java職業訓練での学習成果を形にした、
シンプルながら堅牢な設計を目指したポートフォリオサイトです。
データベースと連携した作品表示に加え、セッション管理や、
最新のセキュリティ標準に準拠した認証機能を実装しています。

---

## 概要

自身の制作実績を一覧で紹介するWebアプリケーションです。
単なる情報表示にとどまらず、バックエンド（Java/PostgreSQL）と連携し、
セッション管理や、Argon2による安全な認証機能を備えています。

---

## 主な機能

- **作品一覧・詳細表示**: 
  データベースから取得した作品データを動的にレンダリングします。
- **ユーザー認証システム**: 
  - **Argon2** による高度なパスワードハッシュ化を実装。
  - カスタムFilterによる未ログイン時のアクセス制御（認証ガード）を行います。
- **ユーザーフレンドリーなUI**:
  - `dialog` タグとCSS `:has()` 擬似クラスを利用した、
    スクロールロック機能付きモーダルを実装。
  - パスワード入力の可視化切り替え（JavaScript）により利便性を向上。

---

## 使用技術

### バックエンド
- **Java 21** (LTS)
- **Jakarta Servlet 6.0 / JSP 3.1**
- **Jakarta JSTL 3.0**
- **Argon2-jvm** (セキュリティ・認証)
- **JDBC / JNDI** (コネクションプールによる効率的なDB接続)

### フロントエンド
- **HTML Living Standard**
- **CSS3** (Modern Features: `:has()`, `flex`, `grid`)
- **JavaScript** (Vanilla JS)

### データベース / インフラ
- **PostgreSQL 16**
- **Apache Tomcat 10.1** (Jakarta EE 10 準拠)
- **Maven** (ビルド・依存関係管理)

---

## ディレクトリ構成

```text
Portfolio
├─ src
│  └─ main
│     ├─ java
│     │  └─ net/live-on/mayaj-kino-portfolio
│     │     ├─ controller (Servlet)
│     │     ├─ filter     (AuthFilter)
│     │     ├─ dao        (DB Access)
│     │     ├─ entity     (Data Model)
│     │     └─ util       (Password/DB Utilities)
│     └─ webapp
│        ├─ META-INF
│        │  └─ context.xml.sample (DB接続テンプレート)
│        ├─ WEB-INF
│        │  ├─ jsp
│        │  └─ web.xml
│        └─ static
│           ├─ css
│           └─ js
├─ sql
│  ├─ schema.sql
│  └─ initial_data.sql
├─ pom.xml
└─ README.md
```

---

## データベース設計

### テーブル構成
- `account`: ユーザー情報（ID、Argon2ハッシュ化済みパスワード）
- `works`: 制作物データ（タイトル、説明、画像パス、URL、公開日）

### 設計のポイント
- **パスワードの安全性**: 
  `Argon2` を採用し、高度な耐解析性を実現しました。
- **コネクションプール**: 
  `JNDI` を活用し、Tomcatのリソースとして効率的にDB接続を管理しています。



---

## 工夫した点・学んだこと

- **セキュリティへの意識**: 
  パスワードの平文保存を避け、最新のハッシュ化アルゴリズムを選択しました。
  また、Filterを用いることで、一貫した認証チェックを適用しています。
- **モダンなCSS実装**: 
  従来はJavaScriptで行っていた「モーダル表示時の背景スクロール禁止」を、
  最新のCSS `:has()` 擬似クラスで完結させ、コードを軽量化しました。
- **Jakarta EE 10 への対応**: 
  Servlet 6.0 等の最新仕様に触れ、`jakarta.servlet` パッケージへの
  移行と変更点について学習しました。

---

## 注意事項

本アプリケーションは学習・ポートフォリオ目的で作成されたものです。