import '@vaadin/vaadin-text-field/vaadin-text-field';

const $_documentContainer = document.createElement('template');

$_documentContainer.innerHTML = `
<custom-style>
  <style>
    html {
    
    }
    vaadin-app-layout vaadin-tab a:hover {
      text-decoration: none;
    }
  </style>
</custom-style>

<dom-module id="app-layout-theme" theme-for="vaadin-app-layout">
  <template>
    <style>
      
    </style>
  </template>
</dom-module>
`;

document.head.appendChild($_documentContainer.content);
