using SafarnamaApplication.Models;
using System.Threading.Tasks;

namespace SafarnamaApplication.Repositories
{
    public interface ICostRepository
    {
        Task<Cost?> GetByPackageIdAsync(int packageId);
    }
}
