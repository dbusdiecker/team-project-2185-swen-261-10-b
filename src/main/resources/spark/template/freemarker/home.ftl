<!DOCTYPE html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <meta http-equiv="refresh" content="10">
  <title>Web Checkers | ${title}</title>
  <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
<div class="page">

  <h1>Web Checkers | ${title}</h1>

  <!-- Provide a navigation bar -->
  <#include "nav-bar.ftl">

  <div class="body">

    <!-- Provide a message to the user, if supplied. -->
    <#include "message.ftl">


    <#if currentUser??>
      <#if player_list??>

          <#list player_list as player>
              <#if currentUser != player>
              <form action="/game" method="POST">
                  <input type="hidden" value="${player.name}" name="opponent">
                  <input type="submit" value="${player.name}">
              </form>
              </#if>
          </#list>
      </#if>
      <#else>
        <p>
            Players online: ${num_online}
        </p>
    </#if>


    <!-- TODO: future content on the Home:
            to start games,
            spectating active games,
            or replay archived games
    -->

  </div>

</div>
</body>

<#if currentUser??>
  <div class="sidenav">
    <h2>Current Games</h2>
  </div>
</#if>

</html>
