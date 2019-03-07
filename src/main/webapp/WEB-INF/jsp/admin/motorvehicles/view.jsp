<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>

<body>

<form id="inputForm">
    <div class="container">
<div class="form-line">
    <span class="view-label">生产设备数量（套）:</span><span class="view-value">${equipmentLeaked.production}</span>
</div>
<div class="form-line">
    <span class="view-label">涉及voc设备数量（套）:</span><span class="view-value">${equipmentLeaked.involvedVoc}</span>
</div>
<div class="form-line">
    <span class="view-label">开展LDAR设备数量（套）:</span><span class="view-value">${equipmentLeaked.developLdar}</span>
</div>
<div class="form-line">
    <span class="view-label">开展LDAR（轮）:</span><span class="view-value">${equipmentLeaked.developLdarTime}</span>
</div>
<div class="form-line">
    <span class="view-label">未开展LDAR设备数量（套）:</span><span class="view-value">${equipmentLeaked.noDevelopLdar}</span>
</div>
<div class="form-line">
    <span class="view-label">豁免装置（套）:</span><span class="view-value">${equipmentLeaked.exemption}</span>
</div>
<div class="form-line">
    <span class="view-label">受控密封点（个）:</span><span class="view-value">${equipmentLeaked.controlledSealedPoint}</span>
</div>
<div class="form-line">
    <span class="view-label">不可达点（个）:</span><span class="view-value">${equipmentLeaked.unreachablePoint}</span>
</div>
<div class="form-line">
    <span class="view-label">估算方法:</span><span class="view-value">${equipmentLeaked.estimationEethod}</span>
</div>
<div class="form-line">
    <span class="view-label">泄露排放量（吨）:</span><span class="view-value">${equipmentLeaked.LeakedEmissions}</span>
</div>
<div class="form-line">
    <span class="view-label">是否达标:</span><span class="view-value">${equipmentLeaked.standard}</span>
</div>
<div class="form-line">
    <span class="view-label">是否达到国内水平:</span><span class="view-value">${equipmentLeaked.domesticLevel}</span>
</div>
<div class="form-line">
    <span class="view-label">消减潜力（吨）:</span><span class="view-value">${equipmentLeaked.reducePotential}</span>
</div>
<div class="form-line">
    <span class="view-label">是否达到国内先进水平:</span><span class="view-value">${equipmentLeaked.domesticAdvancedLevel}</span>
</div>
<div class="form-line">
    <span class="view-label">阀气体（件）:</span><span class="view-value">${equipmentLeaked.valveGas}</span>
</div>
<div class="form-line">
    <span class="view-label">阀轻液体（件）:</span><span class="view-value">${equipmentLeaked.valveLightLiquid}</span>
</div>
<div class="form-line">
    <span class="view-label">阀重液体（件）:</span><span class="view-value">${equipmentLeaked.valveHeavyLiquid}</span>
</div>
<div class="form-line">
    <span class="view-label">泵轻液体（件）:</span><span class="view-value">${equipmentLeaked.pumpLightLiquid}</span>
</div>
<div class="form-line">
    <span class="view-label">泵重液体（件）:</span><span class="view-value">${equipmentLeaked.pumpHeavyLiquid}</span>
</div>
<div class="form-line">
    <span class="view-label">压缩机（件）:</span><span class="view-value">${equipmentLeaked.compressor}</span>
</div>
<div class="form-line">
    <span class="view-label">安全阀气体（件）:</span><span class="view-value">${equipmentLeaked.safetyValveGas}</span>
</div>
<div class="form-line">
    <span class="view-label">安全阀轻液体（件）:</span><span class="view-value">${equipmentLeaked.safetyValveLightLiquid}</span>
</div>
<div class="form-line">
    <span class="view-label">安全阀重液体（件）:</span><span class="view-value">${equipmentLeaked.safetyValveHeavyLiquid}</span>
</div>
<div class="form-line">
    <span class="view-label">法兰气体（件）:</span><span class="view-value">${equipmentLeaked.flangeGas}</span>
</div>
<div class="form-line">
    <span class="view-label">法兰轻液体（件）:</span><span class="view-value">${equipmentLeaked.flangeLightLiquid}</span>
</div>
<div class="form-line">
    <span class="view-label">法兰重液体（件）:</span><span class="view-value">${equipmentLeaked.flangeHeavyLiquid}</span>
</div>
<div class="form-line">
    <span class="view-label">开口管线（件）:</span><span class="view-value">${equipmentLeaked.openPiping}</span>
</div>
<div class="form-line">
    <span class="view-label">采样链接（件）:</span><span class="view-value">${equipmentLeaked.samplingLinks}</span>
</div>
<div class="form-line">
    <span class="view-label">ID:</span><span class="view-value">${equipmentLeaked.id}</span>
</div>
    </div>

</form>
</body>
</html>