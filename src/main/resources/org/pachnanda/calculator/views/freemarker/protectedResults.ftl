<#-- @ftlvariable name="" type="org.pachnanda.calculator.views.ProtectedResultsView" -->
 <html>
    <body>
      <#list protectedResults.results as result>
      <p><a href="/operator/display/${result.id}">${result.name}</a></p>
      </#list>
    </body>
</html>