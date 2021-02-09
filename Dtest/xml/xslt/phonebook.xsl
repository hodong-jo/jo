<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/">
		<html>
			<body>
				<h2> phonebook </h2>
				<table border="1">
					<tr>
					<td>이름</td>
					<td>이메일</td>
					<td>휴대폰</td>
					<td>웹</td>
					</tr>
					<xsl:for-each select="phonebook/person">
					<tr>
						<td><xsl:value-of select="name"/></td>
						<td><xsl:value-of select="email"/></td>
						<td><xsl:value-of select="telephone"/></td>
						<td><xsl:value-of select="web"/></td>
					</tr>
					</xsl:for-each>
				</table>
				<xsl:for-each select="//name">
					이름 : <strong><xsl:value-of select="."/></strong>
				</xsl:for-each>
				<br/>
				<xsl:for-each select="//email">
					이메일 : <strong><xsl:value-of select="."/></strong>
				</xsl:for-each>
				<br/>
				<xsl:for-each select="//telephone">
					휴대폰 : <strong><xsl:value-of select="."/></strong>
				</xsl:for-each>
				<br/>
				<xsl:for-each select="//web">
					웹 : <strong><xsl:value-of select="."/></strong>
				</xsl:for-each>
				<br/>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>