<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">


<xsl:template match="/">
<html>
<body bgcolor="#787878">
<xsl:for-each select="cookbook/recipe">
  <header>
  <h2 align="center"><xsl:value-of select="title"/></h2>
  </header>
    <h3>Ingredient</h3>

    <table border="1">
    <tr bgcolor="#009900">
      <th>name</th>
      <th>amount</th>
    </tr>
    <xsl:apply-templates select="ingredient"/> 
    </table>
    
    <xsl:apply-templates select="preparation"/>
    <xsl:apply-templates select="comment"/>
    <xsl:apply-templates select="nutrition"/>

</xsl:for-each>
</body>
</html>
</xsl:template>


<xsl:template match="ingredient">

  <xsl:variable name="altColor">
    <xsl:choose>
      <xsl:when test="ancestor::ingredient">#CCFFCC</xsl:when>
      <xsl:otherwise>#66FFCC</xsl:otherwise>
    </xsl:choose>
  </xsl:variable> 

  <tr bgcolor="{$altColor}">
    <td><xsl:value-of select="@name"/></td>
    <xsl:if test="@amount != ''">
      <td><xsl:value-of select="@amount"/> <xsl:text> </xsl:text><xsl:value-of select = "@unit"/></td>
    </xsl:if>
  </tr> 

  <xsl:if test="count(ingredient) > 0">
    <table border="1"> 
      <tr bgcolor="#00CC66">
      <th>sub-ingredient</th>
      <th>amount</th>
    </tr>
      <xsl:apply-templates select="ingredient"/>
      <xsl:apply-templates select="preparation"/>
    </table>  
  </xsl:if>  
</xsl:template>


<xsl:template match="preparation">
   <h3>Preparation</h3>
        <ol>
          <xsl:for-each select="step">
          <li><xsl:value-of select="."/></li>
          <br/>
        </xsl:for-each>
        </ol>
</xsl:template>


<xsl:template match="comment">
  <h3>Comment</h3>
  <xsl:value-of select="."/>
</xsl:template>


<xsl:template match="nutrition">
  <h3>Nutrition</h3>
    <table border="2">
    <tr>
      <th>Calories</th>
      <th>Fat</th>
      <th>Carbohydrates</th>
      <th>Protein</th>
      <xsl:if test="@alcohol != ''">
        <th>Alcohol</th>
      </xsl:if>
    </tr>
  <tr>
    <td><xsl:value-of select="@calories"/></td>
     <td><xsl:value-of select="@fat"/></td>
     <td><xsl:value-of select="@carbohydrates"/></td>
     <td><xsl:value-of select="@protein"/></td>
    <xsl:if test="@alcohol != ''"> 
     <td><xsl:value-of select="@alcohol"/></td>
    </xsl:if> 
  </tr>
</table>
</xsl:template>
</xsl:stylesheet>
