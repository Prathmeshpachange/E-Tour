using Microsoft.AspNetCore.Mvc;
using SafarnamaApplication.DTOs;
using SafarnamaApplication.Services;


namespace SafarnamaApplication.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CostController : ControllerBase
    {
        private readonly ICostService _costService;

        public CostController(ICostService costService)
        {
            _costService = costService;
        }

        [HttpGet("package/{packageId}")]
        public async Task<ActionResult<CostDTO>> GetByPackageId(int packageId)
        {
            var cost = await _costService.GetCostByPackageIdAsync(packageId);
            if (cost == null)
                return NotFound();

            return Ok(cost);
        }
    }
}
