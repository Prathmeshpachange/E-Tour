using SafarnamaApplication.DTOs;
using System.Threading.Tasks;

namespace SafarnamaApplication.Services
{
    public interface ICostService
    {
        Task<CostDTO?> GetCostByPackageIdAsync(int packageId);
    }
}
