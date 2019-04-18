<!DOCTYPE html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <meta http-equiv="refresh" content="10">
  <title>Web Checkers | ${title}</title>
  <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<#if currentUser??>
  <div class="sidenav">
    <h2>Current Games</h2>
    <#if opponent_list??>
      <#list opponent_list as player>
        <#if currentUser != player>
          <form action="/game" method="POST">
            <input type="hidden" value="${player.name}" name="opponent">
            <input type="submit" value="${player.name}">
          </form>
        </#if>
       </#list>
    </#if>
  </div>
</#if>

<body>
<div class="page">

  <h1>Web Checkers | ${title}</h1>

  <!-- Provide a navigation bar -->
  <#include "nav-bar.ftl">

  <div class="body">

    <!-- Provide a message to the user, if supplied. -->
    <#include "message.ftl">


    <#if currentUser??>
      <h2>Start A Game</h2>
      <#if (player_list?size == 1)>
        <body>No players are currently online.</body>
        <#else>
          <#list player_list as player>
            <#if currentUser != player>
              <form action="/game" method="POST">
                <input type="hidden" value="${player.name}" name="opponent">
                <input type="submit" value="${player.name}">
              </form>
            </#if>
          </#list>
      </#if>
      <h2>Spectate A Game</h2>
      <#if game_list?has_content>
          <#list game_list as game>
              <form action="/spectator/game" method="POST">
                  <input type="submit" value="${game.redPlayer.name} vs ${game.whitePlayer.name}">
              </form>
          </#list>
          <#else>
            <body>No games are currently active.</body>
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
</html>
