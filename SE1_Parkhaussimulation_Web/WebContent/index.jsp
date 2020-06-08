

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Parkhaus</title>


<script src='https://ccmjs.github.io/mkaul-components/parkhaus/versions/ccm.parkhaus-9.0.0.js'></script>


</head>
<body>
<ccm-parkhaus-9-0-0 server_url = "http://localhost:8080/SE1_Parkhaussimulation_Web/Parkhaus"
					license_max = "30"
					client_categories = '["any","Familie","Frau", "Behinderte"]'
					extra_buttons =  '["sum","avg","Besucheranzahl"]'
					extra_charts = '["chart","familyChart"]'
></ccm-parkhaus-9-0-0>
</body>

</html>
